package com.gxf.app.monitor.ctrl;

import com.gxf.app.monitor.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StuCtrlTest  extends AbstractCtrlTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void list() throws Exception{
        String uri = "/stu/list";
        //  .contentType(MediaType.APPLICATION_JSON_VALUE)
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Student[] stuList = super.mapFromJson(content, Student[].class);
        assertTrue(stuList.length > 0);
    }

    @Test
    public void save() throws Exception{
    }
}