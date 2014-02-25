package com.jbossesb.cookbook.chapter6;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GatewayRecord implements Serializable {
	private static final long serialVersionUID = -2932056789198304007L;
	private Long id;
	private String data;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long f_id) {
		this.id = f_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String f_data) {
		this.data = f_data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String f_status) {
		this.status = f_status;
	}

	public static Builder getBuilder(String f_data, String f_status) {
		return new Builder(f_data, f_status);
	}

	public static class Builder {

		private GatewayRecord built;

		public Builder(String f_data, String f_status) {
			built = new GatewayRecord();
			built.id = new Long(0);
			built.data = f_data;
			built.status = f_status;
		}

		public GatewayRecord build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
