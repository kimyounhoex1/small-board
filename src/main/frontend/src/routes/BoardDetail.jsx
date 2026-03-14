import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/client";

const BoardDetail = () => {
  const navigate = useNavigate();
  const { boardId } = useParams();
  const [boardDetail, setBoardDetail] = useState([]);

  const getBoardDetail = async () => {
    try {
      const resp = await api.get(`api/board/boardid/${boardId}`);
      setBoardDetail(resp.data);
    } catch (error) {
      console.error("게시글 조회 실패", error);
      if (error?.response?.status === 401) {
        navigate("/login");
      }
    }
  };

  const goToBoardList = () => {
    navigate(`../`);
  };

  const goToBoardDetail = (boardId) => {
    navigate(`/board/${boardId}`);
  };

  useEffect(() => {
    getBoardDetail();
  }, [boardId]);

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
