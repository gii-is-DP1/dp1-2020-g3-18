/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Student;
import org.springframework.samples.petclinic.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createStudentForm";

	private final StudentService studentService;

	@Autowired
	public UserController(StudentService clinicService) {
		this.studentService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Student student = new Student();
		model.put("student", student);
		return VIEWS_OWNER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
		}
		else {
			//creating student, user, and authority
			this.studentService.saveStudent(student);
			return "redirect:/";
		}
	}

}
