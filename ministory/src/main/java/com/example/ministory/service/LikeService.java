package com.example.ministory.service;

import com.example.ministory.dto.pushLikesDto;
import com.example.ministory.entity.Likes;
import com.example.ministory.entity.User;
import com.example.ministory.repository.LikeRepository;
import com.example.ministory.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final UserRepository userRepository;

 	@Transactional
	public void pushLikesButton(pushLikesDto request) {
		User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
		user.addLikes(request.getUserId(), request.getPostId());
	}
}
