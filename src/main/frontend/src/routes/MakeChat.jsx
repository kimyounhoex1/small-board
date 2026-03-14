import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/client";

const MakeChat = () => {
  const navigate = useNavigate();

  const [chat, setChat] = useState({
    roomName: "",
    description: "",
  });

  // 로그인 체크 - 토큰이 없으면 로그인 페이지로 이동
  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) {
      navigate("/login");
      return;
    }
  }, [navigate]);

  const onChange = (e) => {
    const { name, value } = e.target;
    setChat({ ...chat, [name]: value });
  };

  const saveChat = async () => {
    try {
      const response = await api.post("api/chat/create", chat);
      alert("채팅방 생성 성공!");
      console.log("생성된 채팅방:", response.data);
      navigate("/chat"); // 채팅방 목록으로 이동
    } catch (error) {
      alert("채팅방 생성 실패");
      console.error(error);
    }
  };
  return (
    <div style={{ padding: "20px", maxWidth: "500px", margin: "0 auto" }}>
      <h2>채팅방 만들기</h2>

      <div style={{ marginBottom: "15px" }}>
        <label>채팅방 이름:</label>
        <input
          type="text"
          name="roomName"
          value={chat.roomName}
          onChange={onChange}
          required
          style={{ width: "100%", padding: "8px", marginTop: "5px" }}
        />
      </div>

      <div style={{ marginBottom: "15px" }}>
        <label>설명:</label>
        <textarea
          name="description"
          value={chat.description}
          onChange={onChange}
          style={{
            width: "100%",
            padding: "8px",
            marginTop: "5px",
            minHeight: "80px",
          }}
        />
      </div>

      <button
        onClick={saveChat}
        style={{
          padding: "10px 20px",
          backgroundColor: "#007bff",
          color: "white",
          border: "none",
          borderRadius: "4px",
          cursor: "pointer",
        }}
      >
        채팅방 만들기
      </button>
    </div>
  );
};

export default MakeChat;
