"use client";

import { useRouter } from "next/navigation";

export default function Home() {
  const router = useRouter();

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="text-center">
        <h1 className="text-4xl font-bold text-gray-800 mb-4">
          Welcome to FitnessTracker
        </h1>
        <p className="text-gray-600 mb-6">
          Track your fitness goals, workouts, and progress all in one place.
        </p>

        <div className="space-x-4">
          <button
            onClick={() => router.push("/login")}
            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
          >
            Login
          </button>
          <button
            onClick={() => router.push("/signup")}
            className="px-6 py-2 bg-gray-300 text-gray-800 rounded-lg hover:bg-gray-400 transition"
          >
            Sign Up
          </button>
        </div>
      </div>
    </div>
  );
}
