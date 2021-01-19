package com.list.music.service;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jlefebure.spring.boot.minio.notification.MinioNotification;
import io.minio.notification.NotificationInfo;

@Service
public class MinionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MinionService.class);

	@MinioNotification({ "s3:ObjectCreated:Post" })
	public void handleUpload(NotificationInfo notificationInfo) {
		LOGGER.info(Arrays
				.stream(notificationInfo.records).map(notificationEvent -> "Receiving event "
						+ notificationEvent.eventName + " for " + notificationEvent.s3.object.key)
				.collect(Collectors.joining(",")));
		System.out.println(notificationInfo);
	}

	@MinioNotification(value = { "s3:ObjectAccessed:Get" })
	public void handleGetPdf(NotificationInfo notificationInfo) {
		LOGGER.info(Arrays
				.stream(notificationInfo.records).map(notificationEvent -> "Receiving event "
						+ notificationEvent.eventName + " for " + notificationEvent.s3.object.key)
				.collect(Collectors.joining(",")));
		System.out.println(notificationInfo);
	}

}
