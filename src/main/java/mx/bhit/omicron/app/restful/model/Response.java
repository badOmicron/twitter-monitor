package mx.bhit.omicron.app.restful.model;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {
	public Response() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2818856716167854316L;
	private int codigo;
	private String mensaje;
	private List<?> información;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<?> getInformación() {
		return información;
	}

	public void setInformación(List<?> información) {
		this.información = información;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [codigo=").append(codigo).append(", mensaje=").append(mensaje).append(", información=")
				.append(información).append("]");
		return builder.toString();
	}

}
