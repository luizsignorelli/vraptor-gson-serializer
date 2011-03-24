package br.com.caelum.vraptor.serialization.gson.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.ExecuteMethodInterceptor;
import br.com.caelum.vraptor.interceptor.ForwardToDefaultViewInterceptor;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.OutjectResult;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.Results;

@Intercepts(after={ExecuteMethodInterceptor.class, OutjectResult.class}, before=ForwardToDefaultViewInterceptor.class)
public class JsonInterceptor implements Interceptor {

	private final MethodInfo info;
	private final Result result;

	public JsonInterceptor(MethodInfo info, Result result) {
		this.info = info;
		this.result = result;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		result.use(Results.json()).from(info.getResult()).recursive().exclude(excludedFieldsNames(method)).serialize();
	}

    private String[] excludedFieldsNames(ResourceMethod method) {
        return method.getMethod().getAnnotation(Json.class).exclude();
    }

    @Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Json.class);
	}
}
