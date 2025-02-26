"use client";

import { useRouter } from "next/navigation";
import { useState } from "react";

export default function Home() {
  const router = useRouter();
  const [user, setUser] = useState(null); // Replace this with actual authentication logic

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <h1 className="text-3xl font-bold text-gray-900">Welcome to Fitness Tracker</h1>

      {user ? (
        <p className="text-gray-700 mt-4">Hello!</p>
      ) : (
        <button
          onClick={() => router.push("/login")}
          className="mt-6 px-6 py-3 bg-blue-600 text-white font-semibold rounded-lg hover:bg-blue-700 transition-all"
        >
          Login
        </button>
      )}
    </div>
  );
}
