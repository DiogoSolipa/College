class Node:

    def __init__(self, key):

        self.key = key
        self.left = None
        self.right = None 

    def toString(node):

        print(node.key + "\n")    

def nextMin(node):

    if node.left is None:
        return node

    return nextMin(node.left)    

def insert(node, key):

    if node is None:
        return Node(key)
    else:
        if node.key is key:
            return None

        elif key < node.key:
            node.left = insert(node.left, key)

        else:
            node.right = insert(node.right, key)

    return node        

def search(node, key):

    if node is None:
        return None

    if node.key is key:
        return node

    elif key < node.key:
        return search(node.left, key)

    else:
        return search(node.right, key)        

    return None

def delete(node, key):

    if node is None:
        return node        

    if key < node.key:
        node.left = delete(node.left, key)
    elif key > node.key:
        node.right = delete(node.right, key)    

    else:
        
        if node.left is None:
            temp = node.right
            node = None
            return temp

        elif node.right is None:
            temp = node.left
            node = None
            return temp
        
        #else:
        temp = nextMin(node.right)
                
        node.key = temp.key

        #searches for the ocurrence of the element that is the new root of the subtree AKA deletes the 2nd ocurrence
        node.rigth = delete(node.right, temp.key)

    return node        


def printInOrder(node):

    if node is not None:        
        printInOrder(node.left)
        print(node.key)
        printInOrder(node.right)

#print("test")

root = None
root = insert(root, 50)
root = insert(root, 30)
root = insert(root, 20)
root = insert(root, 40)
root = insert(root, 70)
root = insert(root, 60)
root = insert(root, 80)

delete(root, 70)    

printInOrder(root)