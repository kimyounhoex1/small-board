import React, {useEffect, useState} from 'react';
import axios from "axios";

// const Board = ({board}) => {
//   return (
//     <div>
//       <b>{board.idx}</b>
//       <b>{board.contents}</b>
//       <b>{board.title}</b>
//       <b>{board.createdAt}</b>
//       <b>{board.creatByMemberId}</b>
//     </div>
//   );
// };

const BoardList = () => {
  const [boardList, setBoardList] = useState([]);

  const getBoardList = async () => {
    const resp = (await axios.get('//localhost:8080/board')).data;
    console.log(resp)
    setBoardList(resp);
    console.log(resp);

    // const pngn = resp.pagination;
    // console.log(pngn);
    }
  

  useEffect(() => {
    getBoardList();
  }, []);

//   return (
//     <div>
//       게시판 목록 출력
//       <ul>
//         {boardList.map((board) => (
//           <li key={board.idx}>{board.idx} {board.title}</li>
//           // board(); 
//         ))}
//       </ul>
//     </div>
//   )
// }
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
            <p className="text-sm text-gray-400 mt-1">작성자 ID: {board.creatByMemberId}</p>
            <p className="text-xs text-gray-400">{board.createdAt}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BoardList;