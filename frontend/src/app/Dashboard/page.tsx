"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

const Dashboard = () => {
  const router = useRouter();
  const [user, setUser] = useState<{ username: string } | null>(null);

  useEffect(() => {
    // Check if user is logged in (for now, assuming token in localStorage)
    const userData = localStorage.getItem("user");
    if (!userData) {
      router.push("/login");
    } else {
      setUser(JSON.parse(userData));
    }
  }, [router]);

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="w-full max-w-lg bg-white p-8 rounded-lg shadow-lg text-center">
        <h1 className="text-2xl font-bold">Welcome, {user?.username || "User"}!</h1>
        <p className="text-gray-600 mt-2">This is your fitness tracker dashboard.</p>
        <button
          onClick={() => {
            localStorage.removeItem("user"); // Log out
            router.push("/login");
          }}
          className="mt-6 px-6 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600"
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Dashboard;
