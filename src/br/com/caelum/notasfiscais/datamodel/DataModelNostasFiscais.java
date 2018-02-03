package br.com.caelum.notasfiscais.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

public class DataModelNostasFiscais extends LazyDataModel<NotaFiscal> {
	private static final long serialVersionUID = 1L;
	@Inject
	private NotaFiscalDao notaFiscalDao;
	
	@PostConstruct
	public void init() {
		super.setRowCount(notaFiscalDao.contaTodos());
	}
	
	
	@Override
	public List<NotaFiscal> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		return notaFiscalDao.listaTodosPaginada(first, pageSize);
	}
}
