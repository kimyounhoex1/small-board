// import React from "react";

// const Header = () => {
//   return (
//     <div>
//       <a href="/">홈</a>
//       &nbsp;&nbsp;|&nbsp;&nbsp;
//       <a href="/board">게시판</a>
//       <hr />
//     </div>
//   );
// };

// export default Header;
import React from "react";

const Header = () => {
  return (
    <header className="bg-white shadow-md p-4 mb-6">
      <div className="max-w-5xl mx-auto flex justify-between items-center">
        <h1 className="text-xl font-bold text-blue-600">📘 Mini Board</h1>
        <nav className="space-x-4">
          <a href="/" className="text-gray-700 hover:text-blue-500">홈</a>
          <a href="/board" className="text-gray-700 hover:text-blue-500">게시판</a>
        </nav>
      </div>
    </header>
  );
};

export default Header;
