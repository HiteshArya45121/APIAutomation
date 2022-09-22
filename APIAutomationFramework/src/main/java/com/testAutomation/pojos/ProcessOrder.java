
package com.testAutomation.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessOrder {

	private Integer orderId;

	private String orderDescription;

	private String orderStatus;

	private String lastUpdatedTimestamp;

	private Boolean specialOrder;

}
