package com.kh.start.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.board.model.dao.BoardMapper;
import com.kh.start.board.model.dto.BoardDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;

	@Override
	public void insert(BoardDTO board, MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardDTO> selectAll(int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO selectOne(Long boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOne(Long boardNo) {
		// TODO Auto-generated method stub
		
	}

}
