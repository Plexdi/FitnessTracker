import Image from "next/image";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-center bg-gray-100 text-center">
      <h1 className="text-4xl font-bold text-gray-800">Welcome to Fitness Tracker</h1>
      <p className="text-gray-600 mt-2">Track your progress and achieve your fitness goals</p>
      <a 
        href="/login" 
        className="mt-4 px-6 py-3 bg-blue-500 text-white font-medium rounded-md hover:bg-blue-600 transition"
      >
        Get Started
      </a>
    </main>
  );
}

