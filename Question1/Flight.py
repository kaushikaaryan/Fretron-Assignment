import matplotlib.pyplot as plt

flight1 = [(1,1), (2,2), (3,3)]
flight2 = [(1,1), (2,4), (3,2)]
flight3 = [(1,1), (4,2), (3,4)]

def plot_flights(flights, colors):
    plt.figure(figsize=(10, 6))
    
    for i, (flight, color) in enumerate(zip(flights, colors)):
        x, y = zip(*flight)
        plt.plot(x, y, marker='o', color=color, linewidth=2, markersize=10, alpha=0.5)

    plt.title('Flight Paths')
    plt.xlabel('X-coordinate')
    plt.ylabel('Y-coordinate')
    plt.grid(True)
    plt.show()

colors = ['r', 'g', 'b']

plot_flights([flight1, flight2, flight3], colors)
