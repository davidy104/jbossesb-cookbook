package com.jbossesb.cookbook.chapter3;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MyBean implements Serializable {
	private static final long serialVersionUID = 777956560143128030L;
	private String beanId;
	private Integer count;
	private String requestMessage;

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public static Builder getBuilder(String beanId, Integer count,
			String requestMessage) {
		return new Builder(beanId, count, requestMessage);
	}

	public static class Builder {

		private MyBean built;

		public Builder(String beanId, Integer count, String requestMessage) {
			built = new MyBean();
			built.beanId = beanId;
			built.count = count;
			built.requestMessage = requestMessage;
		}

		public MyBean build() {
			return built;
		}
	}

	@Override
	public boolean equals(Object obj) {
		EqualsBuilder builder = new EqualsBuilder();
		return builder.append(this.beanId, ((MyBean) obj).beanId).isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder.append(this.beanId).toHashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
