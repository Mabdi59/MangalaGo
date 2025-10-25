# MangalaGo Backend

Spring Boot backend for the MangalaGo travel planner application.

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose (for PostgreSQL)

## Getting Started

### 1. Start the Database

From the project root directory, start PostgreSQL and pgAdmin:

```bash
docker-compose up -d
```

This will start:
- PostgreSQL on `localhost:5432`
- pgAdmin on `http://localhost:5050`

**pgAdmin Access:**
- URL: http://localhost:5050
- Email: admin@mangalago.com
- Password: admin

### 2. Build the Application

```bash
cd backend
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Trips
- `GET /api/trips` - Get all trips
- `GET /api/trips/{id}` - Get trip by ID
- `GET /api/trips/status/{status}` - Get trips by status
- `GET /api/trips/search?destination={name}` - Search trips by destination
- `GET /api/trips/upcoming` - Get upcoming trips
- `POST /api/trips` - Create a new trip
- `PUT /api/trips/{id}` - Update a trip
- `DELETE /api/trips/{id}` - Delete a trip

### Flights
- `GET /api/flights` - Get all flights
- `GET /api/flights/{id}` - Get flight by ID
- `GET /api/flights/trip/{tripId}` - Get flights for a trip
- `POST /api/flights/trip/{tripId}` - Add flight to trip
- `PUT /api/flights/{id}` - Update a flight
- `DELETE /api/flights/{id}` - Delete a flight

### Hotels
- `GET /api/hotels` - Get all hotels
- `GET /api/hotels/{id}` - Get hotel by ID
- `GET /api/hotels/trip/{tripId}` - Get hotels for a trip
- `POST /api/hotels/trip/{tripId}` - Add hotel to trip
- `PUT /api/hotels/{id}` - Update a hotel
- `DELETE /api/hotels/{id}` - Delete a hotel

### Activities
- `GET /api/activities` - Get all activities
- `GET /api/activities/{id}` - Get activity by ID
- `GET /api/activities/trip/{tripId}` - Get activities for a trip
- `POST /api/activities/trip/{tripId}` - Add activity to trip
- `PUT /api/activities/{id}` - Update an activity
- `DELETE /api/activities/{id}` - Delete an activity

### Budget
- `GET /api/budgets/trip/{tripId}` - Get budget for a trip
- `POST /api/budgets/trip/{tripId}` - Create budget for trip
- `PUT /api/budgets/{id}` - Update a budget

### Packing List
- `GET /api/packing/trip/{tripId}` - Get packing list for a trip
- `POST /api/packing/trip/{tripId}` - Create packing list for trip
- `GET /api/packing/{packingListId}/items` - Get packing items
- `POST /api/packing/{packingListId}/items` - Add packing item
- `PUT /api/packing/items/{itemId}` - Update packing item
- `DELETE /api/packing/items/{itemId}` - Delete packing item

### AI Features
- `GET /api/ai/trip-ideas?destination={name}` - Get AI trip ideas
- `GET /api/ai/packing-suggestions?destination={name}&durationDays={days}&season={season}` - Get smart packing suggestions
- `POST /api/ai/optimize-itinerary` - Get itinerary optimization tips
- `GET /api/ai/budget-allocation?totalBudget={amount}&tripType={type}` - Get budget allocation suggestions

## Database Configuration

The application is configured to connect to PostgreSQL with these settings:

```properties
Database: mangalago
Host: localhost:5432
Username: mangalago_user
Password: mangalago_pass
```

You can modify these in `src/main/resources/application.properties`

## Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/mangalago/
│   │   │   ├── config/         # Configuration classes
│   │   │   ├── controller/     # REST controllers
│   │   │   ├── model/          # JPA entities
│   │   │   ├── repository/     # Data repositories
│   │   │   ├── service/        # Business logic
│   │   │   └── MangalaGoApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Development

### Running Tests

```bash
mvn test
```

### Building for Production

```bash
mvn clean package
java -jar target/mangalago-backend-1.0.0.jar
```

## Features

- ✅ Trip management with full CRUD operations
- ✅ Flight tracking and management
- ✅ Hotel booking organization
- ✅ Activity scheduling
- ✅ Budget tracking and allocation
- ✅ Packing list with item management
- ✅ AI-powered trip suggestions
- ✅ Smart packing recommendations
- ✅ Itinerary optimization tips

## License

MIT License - see LICENSE file for details
