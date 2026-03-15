import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/client";

const ChatRoomList = () => {
  const navigate = useNavigate();
  const [chatRoomList, setChatRoomList] = useState([]);

  const getChatRoomList = async () => {
    try {
      const resp = (await api.get(`api/chat/rooms`)).data;
      setChatRoomList(resp);
    } catch (error) {
      console.error("게시글 목록 조회 실패", error);
      // 인증 실패 시 로그인 화면으로 보낸다.
      if (error?.response?.status === 401) {
        navigate("/login");
      }
    }
  };

  const goToChatRoomDetail = (chatRoomId) => {
    navigate(`/chat/${chatRoomId}`);
  };

  useEffect(() => {
    getChatRoomList();
  }, []);

  const moveToHome = (moveToWrite) => {
    navigate("/home");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <h2 className="text-2xl font-bold text-center mb-6 text-blue-600">
        {" "}
        채팅방 목록
      </h2>
      <ul className="space-y-4 max-w-2xl mx-auto">
        {chatRoomList.map((chatRoom) => (
          <li
            key={chatRoom.roomId}
            onClick={() => goToChatRoomDetail(chatRoom.roomId)}
            style={{ cursor: "pointer" }}
            className="bg-white p-4 rounded shadow hover:shadow-md transition"
          >
            <h3 className="text-xl font-semibold text-gray-800">
              {chatRoom.roomName}
            </h3>
            <p className="text-gray-600 mt-2">{chatRoom.description}</p>
            <p className="text-sm text-gray-400 mt-1">
              생성자 ID: {chatRoom.createdBy}
            </p>
            <p className="text-xs text-gray-400">{chatRoom.createdAt}</p>
          </li>
        ))}
      </ul>
      <div>
        <button
          onClick={moveToHome}
          className="px-6 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
        >
          홈으로
        </button>
      </div>
    </div>
  );
};

export default ChatRoomList;
