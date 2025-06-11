package io.github.ninobomba.t4m.test.api.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jira001StepDefinitions {

	@Given ( "A an end-point available to get my activities" )
	public void a_an_end_point_available_to_get_my_activities ( ) {
		log.info ( "given- end-point available to get my activities" );
	}

	@When ( "I query this end-point" )
	public void i_query_this_end_point ( ) {
	}

	@Then ( "It should return the first todo activity od the day" )
	public void it_should_return_the_first_todo_activity_od_the_day ( ) {
		log.info ( "when - I query this end-point" );
	}

}
