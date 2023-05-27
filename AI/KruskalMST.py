class Edge:
    def __init__(self, src, dest, weight):
        self.src = src
        self.dest = dest
        self.weight = weight

class Graph:
    def __init__(self, vertices):
        self.V = vertices
        self.edges = []

    def find(self, parent, i):
        if parent[i] == i:
            return i
        return self.find(parent, parent[i])

    def union(self, parent, rank, x, y):
        root_x = self.find(parent, x)
        root_y = self.find(parent, y)

        if rank[root_x] < rank[root_y]:
            parent[root_x] = root_y
        elif rank[root_x] > rank[root_y]:
            parent[root_y] = root_x
        else:
            parent[root_y] = root_x
            rank[root_x] += 1

    def kruskalMST(self):
        result = []
        i, e = 0, 0

        self.edges = sorted(self.edges, key=lambda edge: edge.weight)
        parent = []
        rank = []

        for node in range(self.V):
            parent.append(node)
            rank.append(0)

        while e < self.V - 1:
            edge = self.edges[i]
            i += 1
            x = self.find(parent, edge.src)
            y = self.find(parent, edge.dest)

            if x != y:
                result.append(edge)
                e += 1
                self.union(parent, rank, x, y)

        print("Edge \tWeight")
        for edge in result:
            print(f"{edge.src} - {edge.dest}\t{edge.weight}")


g = Graph(5)
g.edges.append(Edge(0, 1, 2))
g.edges.append(Edge(0, 3, 6))
g.edges.append(Edge(1, 2, 3))
g.edges.append(Edge(1, 3, 8))
g.edges.append(Edge(1, 4, 5))
g.edges.append(Edge(2, 4, 7))
g.edges.append(Edge(3, 4, 9))

g.kruskalMST()
