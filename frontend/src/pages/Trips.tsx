import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { tripAPI } from '../services/api';
import type { Trip } from '../types';
import './Trips.css';

const Trips = () => {
  const [trips, setTrips] = useState<Trip[]>([]);
  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState<string>('ALL');

  useEffect(() => {
    fetchTrips();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [filter]);

  const fetchTrips = async () => {
    try {
      setLoading(true);
      const response = filter === 'ALL' 
        ? await tripAPI.getAll()
        : await tripAPI.getByStatus(filter);
      setTrips(response.data);
    } catch (error) {
      console.error('Error fetching trips:', error);
    } finally {
      setLoading(false);
    }
  };

  const deleteTrip = async (id: number) => {
    if (window.confirm('Are you sure you want to delete this trip?')) {
      try {
        await tripAPI.delete(id);
        fetchTrips();
      } catch (error) {
        console.error('Error deleting trip:', error);
      }
    }
  };

  const getStatusBadgeClass = (status: string) => {
    switch (status) {
      case 'PLANNING': return 'status-planning';
      case 'CONFIRMED': return 'status-confirmed';
      case 'COMPLETED': return 'status-completed';
      case 'CANCELLED': return 'status-cancelled';
      default: return '';
    }
  };

  return (
    <div className="trips-page">
      <div className="page-header">
        <h1>My Trips</h1>
        <Link to="/create-trip" className="btn btn-primary">+ New Trip</Link>
      </div>

      <div className="filter-bar">
        <button 
          className={filter === 'ALL' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('ALL')}
        >
          All
        </button>
        <button 
          className={filter === 'PLANNING' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('PLANNING')}
        >
          Planning
        </button>
        <button 
          className={filter === 'CONFIRMED' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('CONFIRMED')}
        >
          Confirmed
        </button>
        <button 
          className={filter === 'COMPLETED' ? 'filter-btn active' : 'filter-btn'}
          onClick={() => setFilter('COMPLETED')}
        >
          Completed
        </button>
      </div>

      {loading ? (
        <div className="loading">Loading trips...</div>
      ) : trips.length === 0 ? (
        <div className="no-trips">
          <p>No trips found. Start planning your next adventure!</p>
          <Link to="/create-trip" className="btn btn-primary">Create Your First Trip</Link>
        </div>
      ) : (
        <div className="trips-grid">
          {trips.map((trip) => (
            <div key={trip.id} className="trip-card">
              <div className="trip-header">
                <h3>{trip.name}</h3>
                <span className={`status-badge ${getStatusBadgeClass(trip.status)}`}>
                  {trip.status}
                </span>
              </div>
              <div className="trip-details">
                <p><strong>📍 Destination:</strong> {trip.destination}</p>
                <p><strong>📅 Dates:</strong> {new Date(trip.startDate).toLocaleDateString()} - {new Date(trip.endDate).toLocaleDateString()}</p>
                {trip.description && <p className="trip-description">{trip.description}</p>}
              </div>
              <div className="trip-actions">
                <Link to={`/trips/${trip.id}`} className="btn btn-sm btn-view">View Details</Link>
                <button onClick={() => trip.id && deleteTrip(trip.id)} className="btn btn-sm btn-delete">Delete</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Trips;
