/*
MIT License

Copyright (c) 2021 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.dse.bookstore.controllers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.bookstore.dto.BookDetailDTO;
import co.edu.uniandes.dse.bookstore.dto.EditorialDTO;
import co.edu.uniandes.dse.bookstore.entities.BookEntity;
import co.edu.uniandes.dse.bookstore.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.bookstore.services.BookEditorialService;

/**
 * Clase que implementa el recurso "books/{id}/editorial".
 *
 * @author ISIS2603
 * @version 1.0
 */
@RestController
@RequestMapping("/books")
public class BookEditorialController {

	private final BookEditorialService bookEditorialService;
	private final ModelMapper modelMapper;

	@Autowired
	public BookEditorialController (BookEditorialService bookEditorialService){
		this.bookEditorialService = bookEditorialService;
	}
	

	@Autowired
	public BookEditorialController (ModelMapper modelMapper){
		this.modelMapper = modelMapper;
	}
	 

	/**
	 * Remplaza la instancia de Editorial asociada a un Book.
	 *
	 * @param bookId    Identificador del libro que se esta actualizando. Este debe
	 *                  ser una cadena de dígitos.
	 * @param editorial La editorial que se será del libro.
	 * @return JSON {@link BookDetailDTO} - El arreglo de libros guardado en la
	 *         editorial.
	 */
	@PutMapping(value = "/{bookId}/editorial")
	@ResponseStatus(code = HttpStatus.OK)
	public BookDetailDTO replaceEditorial(@PathVariable("bookId") Long bookId, @RequestBody EditorialDTO editorialDTO)
			throws EntityNotFoundException {
		BookEntity bookEntity = bookEditorialService.replaceEditorial(bookId, editorialDTO.getId());
		return modelMapper.map(bookEntity, BookDetailDTO.class);
	}

}
