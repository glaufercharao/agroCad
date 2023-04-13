package com.agrocad.main.mapper;

import lombok.val;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public interface Mappable {

  default <T> T map(Object source, Class<T> clazz, ExpressionMap... expressionMap) {
    val mapper = mapper();
    addMappings(mapper, source, clazz, expressionMap);
    return mapper.map(source, clazz);
  }

  default ModelMapper mapper() {
    return new ModelMapperConfiguration().modelMapper();
  }

  default <T> void addMappings(ModelMapper mapper, Object source, Class<T> clazz, ExpressionMap[] expressionMap) {
    if (nonNull(expressionMap) && expressionMap.length > 0) {
      Arrays.stream(expressionMap).forEach(exp -> mapper.typeMap(source.getClass(), clazz).addMappings(exp));
    }
  }

  default <T> List<T> map(Collection<?> source, Class<T> clazz, ExpressionMap... expressionMap) {
    return source
        .stream()
        .map(t -> map(t, clazz, expressionMap))
        .collect(Collectors.toList());
  }

  default <T> Set<T> map(Set<?> source, Class<T> clazz, ExpressionMap... expressionMap) {
    return source
        .stream()
        .map(t -> map(t, clazz, expressionMap))
        .collect(Collectors.toSet());
  }

  default <T> Page<T> map(Page<?> source, Class<T> clazz, ExpressionMap... expressionMap) {
    val dtos = source.getContent()
        .stream()
        .map(t -> map(t, clazz, expressionMap))
        .collect(Collectors.toList());

    return new PageImpl<T>(dtos, source.getPageable(), source.getTotalElements());

  }

}