FROM ethereum/client-go:latest

# Install build tools
RUN apk add --no-cache go make git

# Build bootnode
RUN git clone https://github.com/ethereum/go-ethereum && \
    cd go-ethereum && \
    make bootnode && \
    mv build/bin/bootnode /usr/local/bin/ && \
    cd .. && \
    rm -rf go-ethereum

WORKDIR /ethereum
COPY boot.key .
CMD ["bootnode", "--nodekey", "boot.key"]