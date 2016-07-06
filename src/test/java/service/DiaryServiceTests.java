package service;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import application.api.dto.DiaryEntryDto;
import application.api.dto.DiaryService;
import base.AbstractFeelyTest;

public class DiaryServiceTests extends AbstractFeelyTest{
	
	@Autowired
	private DiaryService diaryService;
	
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testIndex() throws Exception {
		
		diaryService.index(new DiaryEntryDto("nikos.strongioglou", ZonedDateTime.now(), "I am feeling quite sad today", 123));
		
		System.out.println("something dump");
		
		DiaryEntryDto diaryEntryDto = diaryService.get("nikos.strongioglou");
		System.out.println(diaryEntryDto.toString());
		
	}
}
