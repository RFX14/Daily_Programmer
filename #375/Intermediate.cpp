#include <iostream>
#include <vector>
#include <cstring>

void flip(std::vector<bool>& vect, int idx) {
    if(idx == 0) {
        vect.at(idx + 1) = !vect.at(idx + 1);
    } else if(idx == vect.size() - 1) {
        vect.at(idx - 1) = !vect.at(idx - 1);
    } else {
        vect.at(idx - 1) = !vect.at(idx - 1);
        vect.at(idx + 1) = !vect.at(idx + 1);
    }
}

void updateUsable(std::vector<bool> cards, std::vector<int>& vect) {
    vect.clear();
    for(int i = 0; i < cards.size(); i++) {
        if(cards.at(i) == 1) {
            vect.push_back(i);
        }
    }
}

//Converts a vect<int> to vect<bool> assuming the vect<int> is made of ASCII values of chars(48 = '0' & 49 = '1')
std::vector<bool> convert(std::vector<int> vect) {
    std::vector<bool> vectFinal{};
    for(int i = 0; i < vect.size(); i++) {
        bool temp = (vect.at(i) == 49)? true : false;
        vectFinal.push_back(temp);
    }
    return vectFinal;
}

int main(int argc, char* argv[]) {
    std::string input = argv[1];
    std::vector<int> cardsInput(input.begin(), input.end());
    std::vector<bool> cards{convert(cardsInput)};
    std::vector<int> usableCards{};

    bool solved{false};
    while(!solved) {
        std::cout << '\n';

        updateUsable(cards, usableCards);

        //Prints out current state of cards
        for(int i = 0; i < cards.size(); i++) {
            std::cout << cards.at(i) << " ";
        }

        std::cout << "\nChoose a card to remove!\n";
        std::cout << "Options: ";

        //Prints out options that user can choose
        for(int i = 0; i < usableCards.size(); i++) {
            std::cout << '[' << usableCards.at(i) << "] ";
        }
        
        int removeIdx{};
        std::cout << "\n>> ";
        std::cin >> removeIdx;

        try {
            flip(cards, removeIdx);
            cards.erase(cards.begin() + removeIdx);
        } catch(std::out_of_range) {
            solved = true;
        }

        //Removes an option from the options stack and renumbers options if necassry
        bool found{false};
        for(int i = 0; i < usableCards.size(); i++) {
            if(usableCards.at(i) == removeIdx) { 
                removeIdx = i; 
                found = true;
            }
            if(found) { usableCards.at(i)--; }
        }

        usableCards.erase(usableCards.begin() + removeIdx);
 
    }

    return 0;
}