import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/client';

const BoardList = () => {
  const navigate = useNavigate();
  const [boardList, setBoardList] = useState([]);

  const getBoardList = async () => {
    try {
      const resp = (await api.get('api/board')).data;
      setBoardList(resp);
    } catch (error) {
      console.error('게시글 목록 조회 실패', error);
      // 인증 실패 시 로그인 화면으로 보낸다.
      if (error?.response?.status === 401) {
        navigate('/login');
      }
    }
  };
  
  const moveToWrite = () => {
    navigate('/write');
  };

  useEffect(() => {
    getBoardList();
  }, []);

return (
    <div className="min-h-screen bg-gray-100 p-8">
      <h2 className="text-2xl font-bold text-center mb-6 text-blue-600"> 게시판 목록</h2>
      <ul className="space-y-4 max-w-2xl mx-auto">
        {boardList.map((board) => (
          <li
            key={board.idx}
            className="bg-white p-4 rounded shadow hover:shadow-md transition"
          >
            <h3 className="text-xl font-semibold text-gray-800">{board.title}</h3>
            <p className="text-gray-600 mt-2">{board.contents}</p>
            <p className="text-sm text-gray-400 mt-1">작성자 ID: {board.createdBy}</p>
            <p className="text-xs text-gray-400">{board.createdAt}</p>
          </li>
        ))}
      </ul>
      <div>
        <button onClick={moveToWrite}
        className="px-6 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
        >글쓰기</button>
      </div>
    </div>
  );
};

export default BoardList;
