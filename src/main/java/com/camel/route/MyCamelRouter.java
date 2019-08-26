package com.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRouter extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
			from("file://D:/MyData/SourceFile?noop=true")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					Message message= exchange.getIn();
					String data=message.getBody(String.class);
					data="Some Modification happened:: mr. "+data;
					Message message2=exchange.getOut();
					message2.setBody(data);
				}
			})			
			.to("file://D:/MyData/DestiFile?fileName=mydata.txt");
		
	}
}
