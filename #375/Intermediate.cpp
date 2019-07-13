#include <iostream>
#include <vector>
#include <cstring>

int main(int argc, char* argv[]) {
    std::string input = argv[1];
    std::vector<char> cards(input.begin(), input.end());
    std::vector<int> usableCards{};

    for(int i = 0; i < cards.size(); i++) {
        if(cards.at(i) == '1') {
            usableCards.push_back(i);
        }
    }

    bool solved{true};
    //while(!solved) {
        std::cout << "Choose a card to remove! \n";
        std::cout << "Options: ";

        for(int i = 0; i < usableCards.size(); i++) {
            std::cout << '[' << usableCards.at(i) << "] ";
        }

        std::cout << "\n>> ";
        int removeIdx{};
        std::cin >> removeIdx;

        cards.erase(cards.begin() + removeIdx);
        //usableCards.erase(usableCards.begin() + );

        
    //}

    return 0;
}