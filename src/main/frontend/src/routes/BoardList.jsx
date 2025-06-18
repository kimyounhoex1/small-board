import React, {useEffect, useState} from 'react';
import axios from "axios";
import { Link, useNavigate } from 'react-router-dom';

const BoardList = () => {
  const navigate = useNavigate();
  const [boardList, setBoardList] = useState([]);

  const getBoardList = async () => {
    const resp = (await axios.get('http://localhost:8080/api/board')).data;
    console.log(resp)
    setBoardList(resp);
    console.log(resp);
    }
  
  const moveToWrite = () => {
    navigate('/write');
  };
  // const boardRegister = async(e) => {
  //   e.preventDefault();
  //   try{
  //     await axios.post('//localhost:8080/board')
  //   }
  // }

  useEffect(() => {
    getBoardList();
  }, []);

return (
    <div className="min-h-screen bg-gray-100 p-8">
      <h2 className="text-2xl font-bold text-center mb-6 text-blue-600">📋 게시판 목록</h2>
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
        <button onClick={moveToWrite}>글쓰기</button>
      </div>
    </div>
  );
};

export default BoardList;

// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import { useNavigate } from "react-router-dom";

// const BoardList = () => {
//   const navigate = useNavigate();
//   const [boardList, setBoardList] = useState([]);

//   const getBoardList = async () => {
//     try {
//       const resp = await axios.get("http://localhost:8080/api/board");
//       setBoardList(resp.data);
//     } catch (e) {
//       console.error("데이터 불러오기 실패", e);
//     }
//   };

//   const moveToWrite = () => {
//     navigate("/write");
//   };

//   useEffect(() => {
//     getBoardList();
//   }, []);

//   return (
//     <div className="min-h-screen bg-gray-100 py-12 px-4 sm:px-6 lg:px-8">
//       <div className="max-w-3xl mx-auto">
//         <h2 className="text-3xl font-bold text-center text-blue-700 mb-8">📋 게시판 목록</h2>
//         <div className="space-y-4">
//           {boardList.map((board) => (
//             <div
//               key={board.idx}
//               className="bg-white p-6 rounded-2xl shadow hover:shadow-xl transition"
//             >
//               <h3 className="text-xl font-semibold text-gray-900">{board.title}</h3>
//               <p className="text-gray-700 mt-2">{board.contents}</p>
//               <div className="text-sm text-gray-400 mt-4 flex justify-between">
//                 <span>작성자 ID: {board.creatByMemberId}</span>
//                 <span>{board.createdAt}</span>
//               </div>
//             </div>
//           ))}
//         </div>
//         <div className="mt-10 text-center">
//           <button
//             onClick={moveToWrite}
//             className="px-6 py-3 bg-blue-500 text-white rounded-full hover:bg-blue-600 transition"
//           >
//             ✏️ 글쓰기
//           </button>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default BoardList;
