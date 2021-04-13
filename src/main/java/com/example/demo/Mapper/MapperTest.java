package com.example.demo.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.example.demo.Dto.Test_Dto;
import com.example.demo.models.Test;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MapperTest {

	public MapperTest INSTANCE = Mappers.getMapper(MapperTest.class);

	@Mapping(source = "tId", target = "testnumber")
	@Mapping(source = "testTitleText", target = "testAdi")
	public Test_Dto testToTest_Dto(Test test);

}
