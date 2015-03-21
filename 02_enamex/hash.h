#ifndef _HASH_H
#define _HASH_H

#include <string.h>
#include <stdlib.h>


typedef struct hash *hash;
hash new_hash(int size);
int add_to_hash(hash* h, char* args[], int size);

#ifdef DEBUG
#include <stdio.h>
void print_hash(hash h, int level);
#endif

#endif
