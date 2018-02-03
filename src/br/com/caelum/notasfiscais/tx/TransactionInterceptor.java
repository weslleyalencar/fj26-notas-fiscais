package br.com.caelum.notasfiscais.tx;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transactional
@Interceptor
public class TransactionInterceptor {
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		
		System.out.println("------>>>>>> INICIOU Interceptador <<<<<--------------");
		
		manager.getTransaction().begin();
		
		Object obj = ic.proceed();
		
		manager.getTransaction().commit();
		
		System.out.println("------>>>>>> COMMITOU Interceptador <<<<<--------------");
		return obj;
	}
	
}
