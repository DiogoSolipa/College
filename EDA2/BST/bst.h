#define IDSIZE 7


struct node;

struct node *new_node(char id [IDSIZE]);
struct node *insert(struct node *root, char id [IDSIZE]);
struct node *search(struct node *root, char id [IDSIZE]);
struct node *deleteNode(struct node *root, char id [IDSIZE]);
struct node *min(struct node *root);
void printInOrder(struct node *root);