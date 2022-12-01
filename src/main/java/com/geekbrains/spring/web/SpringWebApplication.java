package com.geekbrains.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebApplication {

	// Домашнее задание:
	// 1. * Добавить регистрацию новых пользователей
	// 2. Если п. 1 очень сложным показался, то хотя бы текстом опишите план действий

	// текстом
	//1. Добавить ссылку на главную страницу ведущую на новую html страницу регистрации новых пользователей.
	//Ссылка доступна только для админа.
	//2. Создать UserDto для передачи c фронта.
	//3. Создать UserConverter для преобразования UserDto в User и наоборот.
	//4. Добавить метод создания нового пользователя в UserService. Метод принимает сущеность User.
	//5. Создать контроллер UserController с методом создания новых пользователей. Метод принимает UserDto с фронта,
	//преобразует UserDto в User, вызывает метод создания пользователя класса UserController, передавая User.
	//6. На новой html странице регистрации добавить форму добавления с полями нового пользователя и кнопкой "Create".
	//7. Добавить команду регистрации в js, вызывающую метод в контроллере. Метод передает JSON UserDto

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}
}
