import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/client";

const ChatRoomDetail = () => {
  const navigate = useNavigate();
  const { chatRoomId } = useParams();
  const [chatRoomDetail, setChatRoomDetail] = useState({});

  const getChatRoomDetail = async (chatRoomId) => {
    try {
      const resp = (await api.get(`api/chat/${chatRoomId}`)).data;
      setChatRoomDetail(resp);
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
    if (!chatRoomId) return;
    getChatRoomDetail(chatRoomId);
  }, [chatRoomId]);

  /*
    return (
      <div style={{ padding: "20px", maxWidth: "800px", margin: "0 auto" }}>
        <h2>{boardDetail.title}</h2>
        <div
          style={{ margin: "20px 0", padding: "15px", border: "1px solid #ddd" }}
        >
          {boardDetail.contents}
        </div>
        <div style={{ color: "#666", fontSize: "14px" }}>
          작성자: {boardDetail.createdBy} | 작성일: {boardDetail.createdAt}
        </div>
  
        <div style={{ marginTop: "20px" }}>
          <button onClick={() => navigate("/board")}>목록으로 돌아가기</button>
        </div>
      </div>
    );
  };
  
  export default BoardDetail;
  */

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <h2 className="text-2xl font-bold text-center mb-6 text-blue-600">
        {chatRoomDetail.roomName}
      </h2>
      <div
        style={{ margin: "20px 0", padding: "15px", border: "1px solid #ddd" }}
      >
        {chatRoomDetail.roomName}
      </div>
      <div style={{ color: "#666", fontSize: "14px" }}>
        작성자: {chatRoomDetail.createdByNickname ?? "-"} (ID:{" "}
        {chatRoomDetail.createdBy}) | 작성일: {chatRoomDetail.createdAt}
      </div>

      <div style={{ marginTop: "20px" }}>
        <button onClick={() => navigate("/board")}>목록으로 돌아가기</button>
      </div>
    </div>
  );
};

export default ChatRoomDetail;
