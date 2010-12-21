package br.com.caelum.vraptor.serialization.gson;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.serialization.NoRootSerialization;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.view.ResultException;

import com.google.gson.Gson;

@Component
public class GsonSerialization implements JSONSerialization {

	private final class NestedSerializer<T> implements Serializer {
		private final T object;

		private NestedSerializer(T object) {
			this.object = object;
		}

		@Override
		public void serialize() {
			try {
				response.getWriter().write(new Gson().toJson(object));
			} catch (IOException e) {
				throw new ResultException("Unable to serialize data width Gson API", e);
			}
		}

		@Override
		public Serializer recursive() {
			return this;
		}

		@Override
		public Serializer include(String... arg0) {
			return this;
		}

		@Override
		public Serializer exclude(String... arg0) {
			return this;
		}
	}

	private final HttpServletResponse response;

	public GsonSerialization(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public boolean accepts(String format) {
		return "json".equals(format);
	}

	@Override
	public <T> Serializer from(T object) {
		response.setContentType("application/json");
		return serializeWithGsonAPI(object);
	}

	@Override
	public <T> Serializer from(T object, String alias) {
		return from(object);
	}

	@Override
	public <T> NoRootSerialization withoutRoot() {
		return this;
	}

	private <T> Serializer serializeWithGsonAPI(final T object) {
		return new NestedSerializer<T>(object);

	}

}
