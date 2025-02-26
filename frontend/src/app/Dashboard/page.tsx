"use client";

import { useRouter } from "next/navigation";

const Dashboard = () => {
  const router = useRouter();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-lg text-center">
        <h1 className="text-3xl font-bold mb-4">Welcome to Fitness Tracker!</h1>
        <p className="text-gray-600 mb-4">Track your progress and stay fit 💪</p>
        <button
          className="bg-red-600 text-white py-2 px-6 rounded-lg hover:bg-red-700 transition"
          onClick={() => router.push("/login")}
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Dashboard;
