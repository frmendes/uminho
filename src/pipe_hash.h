#ifndef PIPE_HASH_H
#define PIPE_HASH_H

typedef struct pipe_table *PipeTable;

void deletePipeTable(PipeTable pt);

PipeTable newPipeTable(int size);

int pipe_writer(PipeTable pt, char* name, int** ret);

void set_pid(PipeTable pt, char*name, int pid);

void shutdown_children(PipeTable pt);

char* bury_dead_child(PipeTable pt, int pid);

#endif
