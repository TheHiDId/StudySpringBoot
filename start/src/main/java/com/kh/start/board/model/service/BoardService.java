package com.kh.start.board.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.start.board.model.dto.BoardDTO;

public interface BoardService {

	void insert(BoardDTO board, MultipartFile file);
	
	List<BoardDTO> selectAll(int pageNo);
	
	BoardDTO selectOne(Long boardNo);
	
	BoardDTO update(BoardDTO board, MultipartFile file);
	
	void deleteOne(Long boardNo);
}
