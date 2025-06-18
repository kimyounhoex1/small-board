// import React, {useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import axios from 'axios';

// const BoardWrite = () => {
//   const navigate = useNavigate();

//   const [board, setBoard] = useState({
//     title: '',
//     createdByMemberId: '',
//     contents: '',
//   });

//   const { title, createdBy, contents } = board;

//   const onChange = (event) => {
//     const {value, name} = event.target;
//     setBoard({
//       ...board,
//       [name]: value,
//     });
//   };

//   const saveBoard = async() => {
//     await axios.post('//localhost:8080/board/write', board).then((res)=>{
//       alert("등록되었습니다.");
//       navigate('/board');
//     });
//   };

//   const backToList = () => {
//     navigate("/board");
//   };

//   return (
//     <div>
//       <div>
//         <span>제목</span>
//         <input type="text" name="title" value={title} onChange={onChange} />
//       </div>
//       <br />
//       <div>
//         <span>작성자</span>
//         <input
//           type="text"
//           name="createdBy"
//           value={createdBy}
//           onChange={onChange}
//         />
//       </div>
//       <br />
//       <div>
//         <span>내용</span>
//         <textarea
//           name="contents"
//           cols="30"
//           rows="10"
//           value={contents}
//           onChange={onChange}
//         ></textarea>
//       </div>
//       <br />
//       <div>
//         <button onClick={saveBoard}>저장</button>
//         <button onClick={backToList}>취소</button>
//       </div>
//     </div>
//   );
// };

// export default BoardWrite

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const BoardWrite = () => {
  const navigate = useNavigate();

  const [board, setBoard] = useState({
    title: "",
    contents: "",
    creatByMemberId: "", // 여기도 키 이름 맞춰야!
  });

  const onChange = (e) => {
    const { name, value } = e.target;
    setBoard({ ...board, [name]: value });
  };

  const saveBoard = async () => {
    try {
      await axios.post("http://localhost:8080/api/board/write", board);
      alert("✅ 게시글이 등록되었습니다!");
      navigate("/board");
    } catch (e) {
      alert("오류 발생 🥲");
      console.error(e);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4">
      <div className="w-full max-w-xl bg-white p-8 rounded-xl shadow-lg space-y-6">
        <h2 className="text-2xl font-bold text-gray-800 text-center">✏️ 게시글 작성</h2>

        <div>
          <label className="block text-sm font-medium text-gray-700">제목</label>
          <input
            type="text"
            name="title"
            value={board.title}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">작성자 ID</label>
          <input
            type="text"
            name="creatByMemberId"
            value={board.creatByMemberId}
            onChange={onChange}
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">내용</label>
          <textarea
            name="contents"
            value={board.contents}
            onChange={onChange}
            rows="6"
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm p-2"
          />
        </div>

        <div className="flex justify-between mt-6">
          <button
            onClick={saveBoard}
            className="px-6 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            저장
          </button>
          <button
            onClick={() => navigate("/board")}
            className="px-6 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
          >
            취소
          </button>
        </div>
      </div>
    </div>
  );
};

export default BoardWrite;
