# MangalaGo

MangalaGo is a modern, user-friendly travel planner by Mohamed Abdi. Organize flights, hotels, activities, budgets, and packing lists all in one clean application. Built with Java Spring Boot for the backend, React with Vite and TypeScript for the frontend, and PostgreSQL for the database. Features AI-powered trip suggestions, smart packing recommendations, and itinerary optimization tools.

## ✨ Features

- **Trip Management**: Create and organize multiple trips with status tracking (Planning, Confirmed, Completed, Cancelled)
- **Flight Tracking**: Store flight details including airlines, times, confirmation numbers, and seat assignments
- **Hotel Bookings**: Manage hotel reservations with check-in/out dates and contact information
- **Activity Planning**: Schedule activities, tours, and experiences with times and locations
- **Budget Tracking**: Set budgets and track expenses across different categories
- **Packing Lists**: Create and manage packing lists with items organized by category
- **AI-Powered Features**:
  - Smart trip ideas based on destination
  - Intelligent packing suggestions
  - Itinerary optimization tips
  - Budget allocation recommendations

## 🏗️ Architecture

### Backend
- **Java 17** with Spring Boot 3.2.0
- **Spring Data JPA** for database operations
- **PostgreSQL** for data persistence
- **Maven** for dependency management
- RESTful API with full CRUD operations

### Frontend
- **React 18** with TypeScript
- **Vite** for fast development and building
- **React Router** for navigation
- **Axios** for API communication
- **Modern CSS** with Flexbox and Grid

### Database
- **PostgreSQL 15** running in Docker
- **pgAdmin 4** for database management

## 🚀 Getting Started

### Prerequisites

- **Java 17+**
- **Node.js 18+** and npm
- **Docker and Docker Compose**
- **Maven 3.6+**

### Quick Start

1. **Clone the repository**
```bash
git clone https://github.com/Mabdi59/MangalaGo.git
cd MangalaGo
```

2. **Start the database services**
```bash
docker-compose up -d
```

This starts:
- PostgreSQL on `localhost:5432`
- pgAdmin on `http://localhost:5050`

3. **Start the backend**
```bash
cd backend
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

4. **Start the frontend** (in a new terminal)
```bash
cd frontend
npm install
npm run dev
```

The app will be available at `http://localhost:5173`

## 📁 Project Structure

```
MangalaGo/
├── backend/                    # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/mangalago/
│   │   │   │   ├── config/       # Security, CORS configuration
│   │   │   │   ├── controller/   # REST API controllers
│   │   │   │   ├── model/        # JPA entities
│   │   │   │   ├── repository/   # Data repositories
│   │   │   │   ├── service/      # Business logic
│   │   │   │   └── MangalaGoApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── README.md
│
├── frontend/                   # React frontend
│   ├── src/
│   │   ├── components/         # Reusable UI components
│   │   ├── pages/             # Page components
│   │   ├── services/          # API services
│   │   ├── types/             # TypeScript definitions
│   │   ├── App.tsx
│   │   └── main.tsx
│   ├── package.json
│   └── README.md
│
├── docker-compose.yml         # PostgreSQL and pgAdmin setup
├── .gitignore                 # Combined Java/Node gitignore
└── README.md                  # This file
```

## 🔌 API Endpoints

### Trips
- `GET /api/trips` - Get all trips
- `POST /api/trips` - Create a new trip
- `GET /api/trips/{id}` - Get trip by ID
- `PUT /api/trips/{id}` - Update a trip
- `DELETE /api/trips/{id}` - Delete a trip

### Flights, Hotels, Activities
- Similar CRUD operations for each resource
- Link to trips via `/api/{resource}/trip/{tripId}`

### Budget & Packing
- Budget management per trip
- Packing list with items

### AI Features
- `GET /api/ai/trip-ideas?destination={name}` - Get trip suggestions
- `GET /api/ai/packing-suggestions?destination={name}&durationDays={days}&season={season}` - Get packing tips
- `POST /api/ai/optimize-itinerary` - Get itinerary optimization
- `GET /api/ai/budget-allocation?totalBudget={amount}&tripType={type}` - Get budget advice

See individual README files in `backend/` and `frontend/` for more details.

## 🗄️ Database

**PostgreSQL Connection:**
- Host: localhost:5432
- Database: mangalago
- Username: mangalago_user
- Password: mangalago_pass

**pgAdmin Access:**
- URL: http://localhost:5050
- Email: admin@mangalago.com
- Password: admin

## 🛠️ Development

### Backend Development
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Frontend Development
```bash
cd frontend
npm install
npm run dev
```

### Building for Production

**Backend:**
```bash
cd backend
mvn clean package
java -jar target/mangalago-backend-1.0.0.jar
```

**Frontend:**
```bash
cd frontend
npm run build
```

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Linting
```bash
cd frontend
npm run lint
```

## 🐳 Docker

The project includes a Docker Compose configuration for database services:

```bash
# Start services
docker-compose up -d

# Stop services
docker-compose down

# View logs
docker-compose logs -f

# Remove volumes (deletes data)
docker-compose down -v
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

**Mohamed Abdi**

## 🙏 Acknowledgments

- Spring Boot for excellent backend framework
- React and Vite for modern frontend development
- PostgreSQL for reliable data storage
- Docker for easy development setup

