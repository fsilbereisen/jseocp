package de.wbstraining.masterdata.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import de.wbstraining.common.util.QueryConstants;
import de.wbstraining.common.web.controller.AbstractController;
import de.wbstraining.masterdata.persistence.model.Ziehung;
import de.wbstraining.masterdata.service.IZiehungService;
import de.wbstraining.masterdata.util.MasterdataMappings;

@Controller
@RequestMapping(value = MasterdataMappings.Plural.ZIEHUNGS)
public class ZiehungController extends AbstractController<Ziehung, Ziehung> {

	@Autowired
	private IZiehungService service;

	public ZiehungController() {
		super(Ziehung.class);
	}

	// API

	@RequestMapping(value = "/sorted", params = {
		QueryConstants.SORT_BY }, method = RequestMethod.GET)
	@ResponseBody
	public List<Ziehung> findAllSorted(
		@RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
		@RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
		return findAllSortedInternal(sortBy, sortOrder);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Ziehung> findAll(final HttpServletRequest request,
		final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
		return findAllInternal(request, uriBuilder, response);
	}

	// find - one

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Ziehung findOne(@PathVariable("id") final Long id,
		final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
		return findOneInternal(id, uriBuilder, response);
	}

	// create

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid final Ziehung resource,
		final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
		createInternal(resource, uriBuilder, response);
	}

	// update

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") final Long id,
		@RequestBody @Valid final Ziehung resource) {
		updateInternal(id, resource);
	}

	// delete

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		deleteByIdInternal(id);
	}

	// Spring

	@Override
	protected final IZiehungService getService() {
		return service;
	}

}
