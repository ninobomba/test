package io.github.ninobomba.t4m.test.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class TimeResource {

	@GetMapping ( "/time" )
	public HttpEntity < ? > time ( ) {
		var time = LocalDateTime.now ( ).format ( DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss" ) );
		log.info ( "time: {}" , time );
		return new HttpEntity <> ( time );
	}
}
