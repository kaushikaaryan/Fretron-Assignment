import matplotlib.pyplot as plt

# Define the coordinates for each flight
flight1 = [(1,1), (2,2), (3,3)]
flight2 = [(1,1), (2,4), (3,2)]
flight3 = [(1,1), (4,2), (3,4)]

# Function to plot the flights
def plot_flights(flights, colors):
    plt.figure(figsize=(10, 6))
    
    for i, (flight, color) in enumerate(zip(flights, colors)):
        x, y = zip(*flight)
        plt.plot(x, y, marker='o', color=color, linewidth=2, markersize=10, alpha=0.5)
    
    # Adding titles and labels
    plt.title('Flight Paths')
    plt.xlabel('X-coordinate')
    plt.ylabel('Y-coordinate')
    plt.grid(True)
    plt.show()

# Colors for each flight
colors = ['r', 'g', 'b']

# Plotting the flights
plot_flights([flight1, flight2, flight3], colors)
