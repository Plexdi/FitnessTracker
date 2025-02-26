/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  async rewrites() {
    return [
      {
        source: "/dashboard",
        destination: "/dashboard/page.tsx",
      },
    ];
  },
};

module.exports = nextConfig;
