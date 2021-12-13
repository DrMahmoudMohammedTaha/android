import 'package:flutter/material.dart';

void main() async {
  final numbers = FibonacciNumbers();

  runApp(
    MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Fibonacci List'),
        ),
        body: FibonacciListView(numbers),
      ),
    ),
  );
}

class FibonacciNumbers {
  final cache = {0: BigInt.from(1), 1: BigInt.from(1)};

  BigInt get(int i) {
    if (!cache.containsKey(i)) {
      cache[i] = get(i - 1) + get(i - 2);
    }
    return cache[i]!;
  }
}

class FibonacciListView extends StatelessWidget {
  final FibonacciNumbers numbers;

  const FibonacciListView(this.numbers);

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemBuilder: (context, i) {
        return ListTile(
          title: Text('${numbers.get(i)}'),
          onTap: () {
            final snack = SnackBar(
              content: Text('${numbers.get(i)} is '
                  '#$i in the Fibonacci sequence!'),
            );
            ScaffoldMessenger.of(context).showSnackBar(snack);
          },
        );
      },
    );
  }
}