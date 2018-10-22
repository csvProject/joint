/**
 * @author andre
 *
 */
package com.csv.java.net.magja.model.order;

import com.csv.java.net.magja.model.BaseMagentoModel;

public class OrderStatusHistory extends BaseMagentoModel {

	private static final long serialVersionUID = -1012916975638456847L;

	private String incrementId;

	private String createdAt;

	private String isActive;

	private String isCustomerNotified;

	private String status;

	private String comment;

	public String getIncrementId() {
		return incrementId;
	}

	public void setIncrementId(String incrementId) {
		this.incrementId = incrementId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsCustomerNotified() {
		return isCustomerNotified;
	}

	public void setIsCustomerNotified(String isCustomerNotified) {
		this.isCustomerNotified = isCustomerNotified;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see net.magja.magja.model.BaseMagentoModel#serializeToApi()
	 */
	@Override
	public Object serializeToApi() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((incrementId == null) ? 0 : incrementId.hashCode());
		result = prime
				* result
				+ ((createdAt == null) ? 0 : createdAt
						.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result
				+ ((isCustomerNotified == null) ? 0 : isCustomerNotified.hashCode());
		result = prime * result
				+ ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((comment == null) ? 0 : comment.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderStatusHistory other = (OrderStatusHistory) obj;
		if (incrementId == null) {
			if (other.incrementId != null)
				return false;
		} else if (!incrementId.equals(other.incrementId))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (isCustomerNotified == null) {
			if (other.isCustomerNotified != null)
				return false;
		} else if (!isCustomerNotified.equals(other.isCustomerNotified))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderItem [incrementId=" + incrementId + ", createdAt="
				+ createdAt + ", isActive=" + isActive
				+ ", isCustomerNotified=" + isCustomerNotified + ", status="
				+ status + ", comment=" + comment + "]";
	}
}
