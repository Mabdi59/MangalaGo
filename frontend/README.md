# MangalaGo Frontend

Modern React frontend for the MangalaGo travel planner application built with Vite, TypeScript, and React Router.

## Tech Stack

- **React 18** with TypeScript
- **Vite** - Fast build tool and dev server
- **React Router** - Client-side routing
- **Axios** - HTTP client for API requests
- **CSS3** - Modern styling with Flexbox and Grid

## Prerequisites

- Node.js 18+ and npm
- Backend API running on http://localhost:8080

## Getting Started

### 1. Install Dependencies

```bash
cd frontend
npm install
```

### 2. Start Development Server

```bash
npm run dev
```

The application will be available at http://localhost:5173

## Available Scripts

- `npm run dev` - Start development server with hot reload
- `npm run build` - Build for production
- `npm run preview` - Preview production build locally
- `npm run lint` - Run ESLint to check code quality

## Project Structure

```
frontend/
├── src/
│   ├── components/        # Reusable UI components
│   │   ├── Navigation.tsx
│   │   └── Navigation.css
│   ├── pages/            # Page components
│   │   ├── Home.tsx
│   │   ├── Trips.tsx
│   │   ├── CreateTrip.tsx
│   │   └── TripDetails.tsx
│   ├── services/         # API services
│   │   └── api.ts
│   ├── types/            # TypeScript type definitions
│   │   └── index.ts
│   ├── App.tsx           # Main app component
│   ├── App.css
│   ├── main.tsx          # App entry point
│   └── index.css         # Global styles
├── public/               # Static assets
├── index.html
├── package.json
├── tsconfig.json
└── vite.config.ts
```

## Features

### Trip Management
- Create and manage multiple trips
- Filter trips by status (Planning, Confirmed, Completed)
- View detailed trip information
- Delete trips

### Trip Details
- Overview with trip statistics
- Flight tracking with departure/arrival times
- Hotel reservations with check-in/out dates
- Activity scheduling with times and locations
- Budget tracking and allocation

### User Interface
- Clean, modern design with gradient accents
- Responsive layout for mobile and desktop
- Intuitive navigation
- Status badges with color coding
- Interactive tabs for organizing information

## API Integration

The frontend connects to the backend API at `http://localhost:8080/api`. All API calls are managed through the `services/api.ts` file using Axios.

### API Endpoints Used

- **Trips**: CRUD operations for trips
- **Flights**: Manage flight bookings
- **Hotels**: Track hotel reservations
- **Activities**: Schedule trip activities
- **Budget**: Monitor trip expenses
- **Packing**: Manage packing lists
- **AI**: Get smart suggestions for trips

## Configuration

To change the API base URL, edit the `API_BASE_URL` constant in `src/services/api.ts`:

```typescript
const API_BASE_URL = 'http://localhost:8080/api';
```

## Building for Production

```bash
npm run build
```

The production-ready files will be in the `dist/` directory.

## Deployment

The built application can be deployed to any static hosting service:
- Netlify
- Vercel
- AWS S3 + CloudFront
- GitHub Pages
- Firebase Hosting

## Development Tips

1. **Hot Reload**: Changes to source files automatically refresh the browser
2. **TypeScript**: Use proper types for better code quality and IDE support
3. **Component Structure**: Keep components small and focused
4. **CSS Organization**: Each component has its own CSS file
5. **Error Handling**: API errors are logged to the console

## Browser Support

- Chrome/Edge (latest)
- Firefox (latest)
- Safari (latest)
- Mobile browsers (iOS Safari, Chrome Mobile)

## License

MIT License - see LICENSE file for details
