package mx.bhit.omicron.app.restful.model;

import java.io.Serializable;
import java.util.Date;

public class TwitterMessage implements Serializable {
	public TwitterMessage() {
		// TODO Auto-generated constructor stub
	}

	public TwitterMessage(long id, Date fechaCreacion, String usuario, String mensaje) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.usuario = usuario;
		this.mensaje = mensaje;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5556133760694766494L;

	private long id;
	private Date fechaCreacion;
	private String usuario;
	private String mensaje;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TwitterMessage [id=").append(id).append(", fechaCreacion=").append(fechaCreacion)
				.append(", usuario=").append(usuario).append(", mensaje=").append(mensaje).append("]");
		return builder.toString();
	}

}
